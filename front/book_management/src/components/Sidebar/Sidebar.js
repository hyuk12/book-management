/** @jsxImportSource @emotion/react */
import React, {useState} from 'react';
import {css} from "@emotion/react";
import {GrFormClose} from "@react-icons/all-files/gr/GrFormClose";
import ListButton from "./ListButton/ListButton";
import {BiLike} from "@react-icons/all-files/bi/BiLike";
import {BiHome} from "@react-icons/all-files/bi/BiHome";
import {BiListUl} from "@react-icons/all-files/bi/BiListUl";
import {BiLogOut} from "@react-icons/all-files/bi/BiLogOut";
import {useQueryClient} from "react-query";
import {isLoggedOutState} from "../../atoms/Auth/AuthAtom";
import {useRecoilState} from "recoil";

const sidebar = (isOpen) => css`
  position: absolute;
  display: flex;
  left: ${isOpen? "10px":"-240px"};
  flex-direction: column;
  border: 1px solid #dbdbdb;
  border-radius: 10px;
  width: 250px;
  box-shadow: -1px 0 5px #dbdbdb;
  transition: left 1s ease;
  background-color: #fff;

  ${isOpen? "":
          `cursor: pointer;`
  }

  ${isOpen ? "":
          `&:hover{
            left: -220px;
        }`
  }

`;

const header = css`
  display: flex;
  align-items: center;
  margin-bottom: 15px;
  padding: 10px;
`;

const userIcon = css`
  display: flex;
  justify-content: center;
  align-items: center;
  margin-right: 10px;

  border-radius: 8px;
  width: 45px;
  height: 45px;
  background-color: #713fff;
  color: white;
  font-size: 30px;
  font-weight: 600;

`;

const userInfo = css`
  display: flex;
  flex-direction: column;
  justify-content: center;

`;

const userName = css`
  font-size: 18px;
  font-weight: 600;
  padding: 0 5px 5px;
`;

const userEmail = css`
  font-size: 12px;
  font-weight: 600;
`;

const closeButton = css`
  position: absolute;
  top: 10px;
  right: 10px;

  display: flex;
  justify-content: center;
  align-items: center;
  border: 1px solid #dbdbdb;
  padding-left: 0.3px;
  width: 18px;
  height: 18px;
  border-radius: 50%;

  font-size: 12px;
  cursor: pointer;

  &:active{
    background-color: #fafafa;
  }
`;
const main = css`
  padding: 10px;
  border-bottom: 1px solid #dbdbdb;
`;

const footer = css`
  padding: 10px;

`;

const Sidebar = () => {
    const [isOpen, SetIsOpen] = useState(false);
    const [, setIsLoggedOut] = useRecoilState(isLoggedOutState);

    const queryClient = useQueryClient();

    const sidebarOpenClickHandle = () =>{
        if(!isOpen){
            SetIsOpen(true);
        }
    };

    const sidebarCloseClickHandle = () =>{
        SetIsOpen(false);
    };

    const logoutHandler = () => {
        if (window.confirm("로그아웃 하시겠습니까?")) {
            localStorage.removeItem("accessToken");
            setIsLoggedOut(true);
            queryClient.invalidateQueries('principal');
        }
    }

    if (queryClient.getQueryState('principal').status === 'loading'){
        return (<div>is Loading...</div>);
    }

    const roles = queryClient.getQueryData('principal').data.authorities.split(",");

        return (
            <div css={sidebar(isOpen)} onClick={sidebarOpenClickHandle}>
                <header css={header}>
                    <div css={userIcon}>
                        {queryClient.getQueryData('principal').data.name.substring(0,1)}
                    </div>
                    <div css={userInfo}>
                        <h1 css={userName}>{queryClient.getQueryData('principal').data.name}</h1>
                        <p css={userEmail}>{queryClient.getQueryData('principal').data.email}</p>
                    </div>
                    <div css={closeButton} onClick={sidebarCloseClickHandle} ><GrFormClose /></div>
                </header>
                <main css={main}>
                    <ListButton title="Dashboard"><BiHome/></ListButton>
                    <ListButton title="Likes"><BiLike/></ListButton>
                    <ListButton title="Rental"><BiListUl/></ListButton>
                    {roles.includes("ROLE_ADMIN") ? (<ListButton title="RegisterBookList"><BiListUl /></ListButton>) : ""}
                </main>
                <footer css={footer}>
                    <ListButton title="Logout" onClick={logoutHandler}><BiLogOut/></ListButton>
                </footer>
            </div>
        );
};

export default Sidebar;