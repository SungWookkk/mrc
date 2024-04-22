import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useParams } from 'react-router-dom';

function BoardDetail() {
    const [board, setBoard] = useState(null);
    const { id } = useParams();

    useEffect(() => {
        console.log("게시글 ID:", id); // 게시글 ID를 콘솔에 출력하여 디버깅
        const fetchBoardDetail = async () => {
            try {
                if (id) { // id가 유효한 경우에만 요청을 보냄
                    const response = await axios.get(`/api/board/${id}`);
                    setBoard(response.data);
                }
            } catch (error) {
                console.error('게시글 상세 정보를 불러오는 중 오류 발생:', error);
            }
        };
        fetchBoardDetail();
    }, [id]);

    const formatDate = (dateString) => {
        const date = new Date(dateString);
        const year = date.getFullYear();
        const month = String(date.getMonth() + 1).padStart(2, '0');
        const day = String(date.getDate()).padStart(2, '0');
        const hours = String(date.getHours()).padStart(2, '0');
        const minutes = String(date.getMinutes()).padStart(2, '0');
        return `${year}/${month}/${day}/${hours}:${minutes}`;
    };

    return (
        <div>
            {board ? (
                <div>
                    <h1>{board.title}</h1>
                    <p>작성일자: {formatDate(board.regdate)}</p>
                    <p>작성자: {board.writer ? board.writer.userNick : '작성자 미상'}</p>
                    <p>{board.content}</p>
                </div>
            ) : (
                <p>게시글을 불러오는 중입니다...</p>
            )}
        </div>
    );
}

export default BoardDetail;
