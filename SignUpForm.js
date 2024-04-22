import React, { useState, useContext } from 'react';
import axios from 'axios';
import { useHistory } from 'react-router-dom';
import { AuthContext } from './AuthContext';

const SignUpForm = () => {
    const [userData, setUserData] = useState({
        userId: '',
        userPassword: '',
        userPasswordCheck: '',
        userPhoneNumber: '',
        userEmail: '',
        userNick: ''
    });
    const history = useHistory();
    const { updateToken } = useContext(AuthContext); // AuthContext에서 updateToken 함수를 가져옴

    const handleChange = (e) => {
        const { name, value } = e.target;
        setUserData(prevState => ({
            ...prevState,
            [name]: value
        }));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post('http://localhost:8080/api/users/save', userData);
            console.log('User registered successfully:', response.data);
            updateToken(response.data.token); // 회원가입 성공 시 토큰을 업데이트
            history.push('/login');
        } catch (error) {
            console.error('Error registering user:', error);
        }
    };

    return (
        <div className="signup-form">
            <h1 className="title">회원가입</h1>
            <form onSubmit={handleSubmit}>
                <input type="text" name="userId" value={userData.userId} onChange={handleChange} placeholder="아이디" required/>
                <input type="text" name="userNick" value={userData.userNick} onChange={handleChange} placeholder="닉네임" required/>
                <input type="password" name="userPassword" value={userData.userPassword} onChange={handleChange} placeholder="비밀번호" required/>
                <input type="password" name="userPasswordCheck" value={userData.userPasswordCheck} onChange={handleChange} placeholder="비밀번호 확인" required/>
                <input type="text" name="userPhoneNumber" value={userData.userPhoneNumber} onChange={handleChange} placeholder="휴대폰 번호" required/>
                <input type="text" name="userEmail" value={userData.userEmail} onChange={handleChange} placeholder="이메일" required/>
                <input className="submit-btn" type="submit" value="회원가입"/>
            </form>
        </div>
    );
}

export default SignUpForm;
