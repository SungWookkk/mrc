import React, { createContext, useState } from 'react';

const AuthContext = createContext();

const AuthProvider = ({ children }) => {
    const [token, setToken] = useState(localStorage.getItem('token') || '');

    const updateToken = (newToken) => {
        setToken(newToken);
        localStorage.setItem('token', newToken);
    };

    return (
        <AuthContext.Provider value={{ token, updateToken }}>
            {children}
        </AuthContext.Provider>
    );
};

export { AuthProvider, AuthContext };
