// BoardForm.js
import React, { useState, useContext } from 'react';
import axios from 'axios';
import { useHistory } from 'react-router-dom';
import { AuthContext } from './AuthContext';

function BoardForm() {
    const [title, setTitle] = useState('');
    const [content, setContent] = useState('');
    const history = useHistory();
    const { token } = useContext(AuthContext);
    const userNick = localStorage.getItem('userNick');

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            const response = await axios.post(
                '/api/board/write',
                {
                    title: title,
                    content: content,
                    userNick: userNick, // 사용자의 닉네임을 함께 전달
                },
                {
                    headers: {
                        Authorization: `Bearer ${token}`,
                    },
                }
            );

            console.log(response.data);
            // 게시글 작성 후 게시글 목록 페이지로 이동
            history.push('/boardlist');
        } catch (error) {
            console.error('게시글 작성 중 오류:', error);
        }
    }

    return (
        <form onSubmit={handleSubmit}>
            <div>
                <label>Title:</label>
                <input type="text" value={title} onChange={(e) => setTitle(e.target.value)} />
            </div>
            <div>
                <label>Content:</label>
                <textarea value={content} onChange={(e) => setContent(e.target.value)} />
            </div>
            <button type="submit">Submit</button>
        </form>
    );
}

export default BoardForm;
