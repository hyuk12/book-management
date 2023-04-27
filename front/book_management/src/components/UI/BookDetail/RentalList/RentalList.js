/** @jsxImportSource @emotion/react */
import React from 'react';
import {css} from "@emotion/react";
import {useMutation, useQuery, useQueryClient} from "react-query";
import axios from "axios";

const table = css`
  border: 2px solid #dbdbdb;
`;

const thAndTd = css`
  border: 1px solid #dbdbdb;
  padding: 5px 10px;
  text-align: center;
`;

const RentalList = ({ bookId }) => {
    const queryClient = useQueryClient();
    const memberId = queryClient.getQueryData("principal").data.memberId;
    const getRentalList = useQuery(["getRentalList"], async () => {
        const option = {
            headers: {
                Authorization: localStorage.getItem("accessToken")
            }
        }
        return await axios.get(`http://localhost:8080/book/${bookId}/rental/list`, option);

    });

    const rentalBook = useMutation(async () => {

    })
    const returnBook = useMutation(async () => {

    })
    if(getRentalList.isLoading) {
        return <div>로딩중...</div>
    }

    return (
        <>
            <table css={table}>
                <thead>
                    <tr>
                        <th css={thAndTd}>도서번호</th>
                        <th css={thAndTd}>도서명</th>
                        <th css={thAndTd}>상태</th>
                    </tr>
                </thead>
                <tbody>
                    {getRentalList.data.data.map((rental) => {
                        return (
                            <tr key={rental.bookListId}>
                                <td css={thAndTd}>{rental.bookListId}</td>
                                <td css={thAndTd}>{rental.bookName}</td>
                                {rental.rentalStatus ?
                                    (<td css={thAndTd}>대여가능<button>대여</button></td>)
                                    :(<td css={thAndTd}>{rental.memberId === memberId
                                        ? (<button>반납</button>) : ""}</td>)}
                            </tr>
                        )
                    })}
                </tbody>
            </table>
        </>
    );
};

export default RentalList;