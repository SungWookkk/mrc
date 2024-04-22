// App.js
import React from 'react';
import { BrowserRouter as Router, Route, Switch, Link } from 'react-router-dom';
import SignUpForm from './SignUpForm';
import LoginForm from './LoginForm';
import LoginSuccess from './LoginSuccess';
import MainNavigation from "./MainNavigation";
import { AuthProvider } from './AuthContext'; // AuthProvider import 추가
import BoardForm from "./BoardForm";
import BoardList from "./BoardList";
import BoardDetail from "./BoardDetail"; // 새로 추가한 파일

function App() {
    return (
        <Router>
            <AuthProvider> {/* AuthProvider로 감싸기 */}
                <div>
                    <MainNavigation />
                    <Switch>
                        <Route path="/signup">
                            <SignUpForm />
                        </Route>
                        <Route path="/login">
                            <LoginForm />
                        </Route>
                        <Route path="/success">
                            <LoginSuccess />
                        </Route>
                        <Route path="/board/write" component={BoardForm}>
                            <BoardForm />
                        </Route>
                        <Route path="/boardlist" component={BoardList}> {/* 수정 */}
                            <BoardList />
                        </Route>
                        <Route path="/board/:id" component={BoardDetail} /> {/* 새로 추가 */}
                        <Route exact path="/">
                            <Link to="/boardlist">게시판 목록으로 이동</Link> {/* 수정 */}
                        </Route>
                        <Route path="/board/:id">
                            <BoardDetail />
                        </Route>
                    </Switch>
                </div>
            </AuthProvider>
        </Router>
    );
}

export default App;
