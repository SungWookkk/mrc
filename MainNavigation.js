import React from 'react';
import { Link } from 'react-router-dom';

function MainNavigation() {
    return (
        <nav>
            <ul>
                <li>
                    <Link to="/signup">회원가입</Link>
                </li>
                <li>
                    <Link to="/login">로그인</Link>
                </li>
                <li>
                    <Link to="/board/write">게시판 작성</Link>
                </li>
            </ul>
        </nav>
    );
}

export default MainNavigation;