/** @jsxImportSource @emotion/react */
import React from 'react';
import {css} from "@emotion/react";
import {AiOutlineLike} from "@react-icons/all-files/ai/AiOutlineLike";

const cardContainer = css`
  display: flex;
  flex-direction: column;
  align-items: center;
  margin: 20px;
  border: 1px solid #dbdbdb;
  border-radius: 7px;
  width: 300px;
  
  box-shadow: 0 0 5px #dbdbdb;
  cursor: pointer;
  
  &:hover {
    box-shadow: 0 0 10px #dbdbdb;
  }
  
  &:active {
    background-color: #fafafa;
  }
`;

const header = css`
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 20px;
`;

const titleText = css`
  font-weight: 600;
`;

const main = css`
  display: flex;
  flex-direction: column;
  align-items: center;
  
  width: 100%;
`;
const imgBox = css`
  display: flex;
  justify-content: center;
  align-items: center;
  margin-bottom: 10px;
  border-radius: 7px;
  padding: 5px;
  height: 200px;
  
  overflow: hidden;
  box-shadow: 0px 7px 7px #dbdbdb;
  background-color: #fafafa;
`;
const img = css`
  height: 100%;
`;

const footer = css`
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 20px;
  font-size: 14px;
  font-weight: 600;

`;

const like = css`
  display: flex;
  justify-content: center;
  align-items: center;

  margin-bottom: 10px;
  border: 1px solid #dbdbdb;
  border-radius: 7px;
  padding: 10px;
  height: 30px;

  font-weight: 600;
  cursor: pointer;
  background-color: #fff;
  box-shadow: 0px 2px 7px #dbdbdb;
  
`;

const likeIcon = css`
  padding-right: 5px;
`;

const BookCard = () => {
    return (
        <div css={cardContainer}>
            <header css={header}>
                <h1 css={titleText}>내 통장 사용 설명서 (통장 7개로 시작하는 세상에서 제일 쉬운 재테크)</h1>
            </header>
            <main css={main}>
                <div css={imgBox}>
                    <img css={img} src="https://epbook.eplib.or.kr/resources/images/opms/9788901101101.jpg" alt="내 통장 사용 설명서 (통장 7개로 시작하는 세상에서 제일 쉬운 재테크)" />
                </div>
            </main>
            <footer css={footer}>
                <div css={like}><div css={likeIcon}><AiOutlineLike /></div> 추천: 10 </div>
                <h2>저자명: 이천</h2>
                <h2>출판사: 웅진윙스</h2>
            </footer>
        </div>
    );
};

export default BookCard;