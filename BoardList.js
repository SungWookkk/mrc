    import React, { useEffect, useState } from 'react';
    import axios from 'axios';
    import { Link } from 'react-router-dom';

    function BoardList() {
        const [boards, setBoards] = useState([]);

        useEffect(() => {
            const fetchBoards = async () => {
                try {
                    const response = await axios.get('/api/board/all');
                    setBoards(response.data);
                } catch (error) {
                    console.error('게시글 목록을 불러오는 중 오류 발생:', error);
                }
            };
            fetchBoards();
        }, []);

        // 작성일자 형식 변환 함수
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
                <h2>게시글 목록</h2>
                <ul>
                    {boards.map(board => (
                        <li key={board.boardNum}>
                            <Link to={`/board/${board.boardNum}`}>{board.title}</Link>
                        </li>
                    ))}
                </ul>
            </div>
        );
    }

    export default BoardList;
