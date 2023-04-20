/** @jsxImportSource @emotion/react */
import React, {useState} from 'react';
import LoginInput from "../../components/UI/Login/LoginInput/LoginInput";
import {FiUser} from "@react-icons/all-files/fi/FiUser";
import {FiLock} from "@react-icons/all-files/fi/FiLock";
import {css} from "@emotion/react";
import {Link, useNavigate} from "react-router-dom";
import {BiRename} from "@react-icons/all-files/bi/BiRename";
import axios from "axios";

const container = css `
    display: flex;
    flex-direction: column;
    align-items: center;
    padding: 80px 30px;
`;

const logo = css `
    margin: 50px 0;
    font-size: 34px;
    font-weight: 600;
`;

const mainContainer = css `
    display: flex;
    flex-direction: column;
    align-items: center;
    border: 1px solid #dbdbdb;
    border-radius: 10px;
    padding: 40px 20px;
    width: 400px;
`;

const authForm = css `
    width: 100%;
`;

const inputLabel = css `
    margin-left: 5px;
    font-size: 12px;
    font-weight: 600;

`;

const registerButton = css `
    margin: 10px 0;
    border: 1px solid #dbdbdb;
    border-radius: 7px;
    width: 100%;
    height: 50px;
    background-color: #fff;
    font-weight: 900;
    cursor: pointer;
  
  &:hover {
    background-color: #fafafa;
  }
  
  &:active {
      background-color: #eee;
  }
`;

const loginMessage = css`
    margin-top: 20px;
    font-size: 14px;
    font-weight: 600;
    color: #777;
`;

const register = css`
    margin-top: 10px;
    font-weight: 600;
`;

const errorMsg = css`
  margin-left: 5px;
  margin-bottom: 20px;
  color: #ff0000;
  font-size: 12px;
  font-weight: 600;
`;

const Register = () => {
    const navigate = useNavigate();

    const [registerUser, setRegisterUser] = useState({email: '', password: '', name: ''});
    const [errorMessages, setErrorMessages] = useState({email: '', password: '', name: ''});

    const onChangeHandle = (e) => {
        const {name, value} = e.target;
        setRegisterUser({
            ...registerUser,
            [name]: value
        });
    }

    // promise 에만 await 을 붙일 수 있다.
    const registerSubmit = async () => {
        const data = {
            ...registerUser
        }

        const option = {
            headers: {
                'Content-Type': 'application/json'
            }
        }
        try {
            await axios.post('http://localhost:8080/auth/signup', JSON.stringify(data), option);
            setErrorMessages({email: '', password: '', name: ''});
            alert('회원가입이 완료되었습니다. 로그인 페이지로 이동합니다.');
            navigate('/login');
        }catch (e) {
            setErrorMessages({email: '', password: '', name: '', ...e.response.data.errorData});
        }

        //     .then(response => {
        //         setErrorMessages({email: '', password: '', name: ''});
        //         console.log(response);
        //
        //     })
        //     .catch(error => {
        //         setErrorMessages({email: '', password: '', name: '', ...error.response.data.errorData});
        // });
    }

    return (
        <div css={container}>
          <header>
              <h1 css={logo}>SIGN UP</h1>
          </header>

          <main css={mainContainer}>
            <div css={authForm}>
                <label css={inputLabel}>Email</label>
                <LoginInput type={"email"} placeholder={"Type your Email"} onChange={onChangeHandle} name={"email"}>
                    <FiUser />
                </LoginInput>
                <div css={errorMsg}>
                    {errorMessages.email}
                </div>

                <label css={inputLabel}>Password</label>
                <LoginInput type={"password"} placeholder={"Type your password"} onChange={onChangeHandle} name={"password"}>
                    <FiLock />
                </LoginInput>
                <div css={errorMsg}>
                    {errorMessages.password}
                </div>

                <label css={inputLabel}>Name</label>
                <LoginInput type={"text"} placeholder={"Type your name"} onChange={onChangeHandle} name={"name"}>
                    <BiRename/>
                </LoginInput>
                <div css={errorMsg}>
                    {errorMessages.name}
                </div>
                <button css={registerButton} onClick={registerSubmit}>REGISTER</button>
            </div>
          </main>

          <div css={loginMessage}>Already a user?</div>

          <footer>

              <div css={register}><Link to={'/login'}>LOGIN</Link></div>
          </footer>
        </div>
    );
};

export default Register;