/** @jsxImportSource @emotion/react */
import React from 'react';
import {css} from "@emotion/react";

const list = css`
  display: flex;
  align-items: center;
  border-radius: 7px;
  padding: 5px;
  width: 100%;
  cursor: pointer;
  
  &:hover {
    background-color: #fafafa;
  }
  
  &:active {
    background-color: #f5f5f5;
  }
`;

const listIcon = css`
  display: flex;
  justify-content: center;
  align-items: center;
  width: 40px;
  height: 40px;
`;

const listTitle = css`
  display: flex;
  align-items: center;
  font-weight: 600;
`;

const ListButton = ({ children, title, onClick }) => {
    return (
        <div css={list} onClick={onClick}>
            <div css={listIcon}>{ children }</div>
            <div css={listTitle}>{ title }</div>
        </div>
    );
};

export default ListButton;