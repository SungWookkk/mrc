import React, { useState, useContext } from 'react';
import axios from 'axios';
import { useHistory } from 'react-router-dom';
import { AuthContext } from './AuthContext';
import './AuthContext';

const LoginForm = ({ setToken }) => {
    const [userId, setUserId] = useState('');
    const [userPassword, setUserPassword] = useState('');
    const [error, setError] = useState('');
    const history = useHistory();
    const { updateToken } = useContext(AuthContext); // AuthContext를 제대로 가져옴

    const handleLogin = async () => {
        try {
            const response = await axios.post('/api/login', {
                userId: userId,
                userPassword: userPassword,
            });

            if (response.data && response.data.token) {
                // 로그인 성공 시 토큰과 닉네임을 받아와서 상태에 저장
                updateToken(response.data.token);
                localStorage.setItem('userNick', response.data.userNick);
                // 로그인 성공 시 메인 페이지로 이동
                history.push('/');
            } else {
                setError('로그인 정보가 올바르지 않습니다. 다시 확인해주세요.');
            }
        } catch (error) {
            console.error('로그인 중 오류 발생:', error);
            setError('로그인에 실패했습니다. 다시 시도해주세요.');
        }
    };

    return (
        <div className="login-form">
            <h2>로그인</h2>
            <div>
                <input
                    type="text"
                    value={userId}
                    onChange={(e) => setUserId(e.target.value)}
                    placeholder="아이디"
                />
            </div>
            <div>
                <input
                    type="password"
                    value={userPassword}
                    onChange={(e) => setUserPassword(e.target.value)}
                    placeholder="비밀번호"
                />
            </div>
            <button onClick={handleLogin}>로그인</button>
            {error && <p className="error-message">{error}</p>}
        </div>
    );
};

export default LoginForm;
