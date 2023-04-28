import React, {useEffect} from 'react';
import {Navigate} from "react-router-dom";
import {useQuery} from "react-query";
import axios from "axios";
import {useRecoilState} from "recoil";
import {refreshState} from "../../../atoms/Auth/AuthAtom";


const AuthRouteReactQuery = ({ path, element }) => {
    const [refresh, setRefresh] = useRecoilState(refreshState);

    const { data,isLoading } = useQuery(["authenticated"], async () => {
        const accessToken = localStorage.getItem("accessToken");
        const response = await axios.get('http://localhost:8080/auth/authenticated', {params: {accessToken}});
        return response;
    }, {
        enabled: refresh
    });

    const principal = useQuery(["principal"], async ()=> {
        const accessToken = localStorage.getItem("accessToken");
        const response = await axios.get("http://localhost:8080/auth/principal", {params: {accessToken}});
        return response;
    },{
        enabled : data !== undefined
    });

    useEffect(() => {
        if (!refresh) {
            setRefresh(true);
        }
    }, [refresh]);

    if(isLoading) {
        return (<div>loading...</div>);
    }

    if(principal.data !== undefined) {
        const roles = principal.data.data.authorities.split(",");

        if (path.startsWith("/admin") && !roles.includes("ROLE_ADMIN")) {
            alert("관리자만 접근 가능합니다.");

            return <Navigate to="/" />;
        }
    }

    if(!isLoading) {
        const permitAll = ["/login", "/register", "/password/forgot"];

        if(!data.data) {
            if(permitAll.includes(path)) {
                return  element;
            }
            return <Navigate to="/login" />;
        }

        if(permitAll.includes(path)) {
            return  <Navigate to="/" />;
        }

        return element;

    }
};

export default AuthRouteReactQuery;