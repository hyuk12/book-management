/** @jsxImportSource @emotion/react */
import React from 'react';
import {css} from "@emotion/react";

const input = css`
    border: none;
    outline: none;
    padding: 5px 10px;
    width: 100%;
    height: 40px;
`;



const Input = ({ type, placeholder, onChange }) => {
    return (

        <>
            <input css={input}
                   type={type}
                   placeholder={placeholder}
                   onChange={onChange} />
        </>

    );
};

export default Input;