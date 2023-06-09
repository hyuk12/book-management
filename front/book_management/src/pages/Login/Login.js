/** @jsxImportSource @emotion/react */
import React, {useState} from 'react';
import LoginInput from "../../components/UI/Login/LoginInput/LoginInput";
import {FiUser} from "@react-icons/all-files/fi/FiUser";
import {FiLock} from "@react-icons/all-files/fi/FiLock";
import {css} from "@emotion/react";
import {Link, useNavigate} from "react-router-dom";
import {GrGoogle} from "@react-icons/all-files/gr/GrGoogle";
import {SiKakao, SiNaver} from "react-icons/si";
import axios from "axios";
import {useRecoilState} from "recoil";
import {refreshState} from "../../atoms/Auth/AuthAtom";

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

const forgotPassword = css `
    display: flex;
    justify-content: flex-end;
    align-items: center;
  
    margin-bottom: 45px;
    width: 100%;
  
    font-size: 12px;
    font-weight: 600;
  
`;

const loginButton = css `
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

const signupMessage = css`
    margin-top: 20px;
    font-size: 14px;
    font-weight: 600;
    color: #777;
`;

const oauth2Container = css `
    display: flex;
    justify-content: center;
    align-items: center;
    margin: 20px;
    width: 100%;
    
`;

const oauth2 = (provider) => css` 
    display: flex;
    justify-content: center;
    align-items: center;
    margin: 0 10px;
    border: 3px solid ${provider === 'google' ? '#0075ff' : provider === 'naver' ? '#19ce60' : '#ffdc00'};
    border-radius: 50%;
    width: 50px;
    height: 50px;
    font-size: ${provider === 'kakao' ? '30px' : '20px'};
    cursor: pointer;
  
    &:hover {
        background-color: ${provider === 'google' ? '#0075ff' : provider === 'naver' ? '#19ce60' : '#ffdc00'};
    }
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


const Login = () => {

    const [loginUser, setLoginUser] = useState({email: '', password: ''});
    const [errorMessages, setErrorMessages] = useState({email: '', password: ''});
    const [refresh, setRefresh] = useRecoilState(refreshState);
    const navigate = useNavigate();

    const onChangeHandler = (e) => {
        const { name, value } = e.target;
        setLoginUser (
            {
                ...loginUser,
                [name]: value
            }
        )
    }

    const loginHandler = async () => {
        const data = { ...loginUser}
        const option = {
            headers: {
                'Content-Type': 'application/json'
            }
        }
        try {
            const response = await axios.post('http://localhost:8080/auth/login', JSON.stringify(data), option);
            setErrorMessages({email: '', password: ''});

            const accessToken = response.data.grantType + " " + response.data.accessToken;
            localStorage.setItem('accessToken', accessToken);
            setRefresh(false);
            navigate('/');
        }catch (error) {
            setErrorMessages({email: '', password: '', ...error.response.data.errorData});
        }


    }

    return (
        <div css={container}>
          <header>
              <h1 css={logo}>Login</h1>
          </header>

          <main css={mainContainer}>
            <div css={authForm}>
                <label css={inputLabel}>Email</label>
                <LoginInput type={"email"} placeholder={"Email is required"} onChange={onChangeHandler} name={"email"}>
                    <FiUser />
                </LoginInput>
                <div css={errorMsg}>
                    {errorMessages.email}
                </div>
                <label css={inputLabel}>password</label>
                <LoginInput type={"password"} placeholder={"password is required"} onChange={onChangeHandler} name={"password"}>
                    <FiLock />
                </LoginInput>
                <div css={errorMsg}>
                    {errorMessages.password}
                </div>
                <div css={forgotPassword}>
                    <Link to={"/forgot/password"}>Forgot Password?</Link>
                </div>
                <button css={loginButton} onClick={loginHandler}>LOGIN</button>
            </div>
          </main>

          <div css={signupMessage}>Or Sign Up Using</div>

          <div css={oauth2Container}>
                <div css={oauth2('google')}><GrGoogle/></div>
                <div css={oauth2('naver')}><SiNaver/></div>
                <div css={oauth2('kakao')}><SiKakao/></div>
          </div>

          <div css={signupMessage}>Or Sign Up Using</div>

          <footer>

              <div css={register}><Link to={'/register'}>SIGN UP</Link></div>
          </footer>
        </div>
    );
};

export default Login;