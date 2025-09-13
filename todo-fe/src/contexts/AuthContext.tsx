import React, { createContext, useState, useEffect, useCallback } from 'react';
import type { AuthResponse, LoginRequest, RegisterRequest } from '../models/Auth';
import { storageService } from '../utils/token';
import { authService } from '../services/authService';

interface AuthContextType {
  user: AuthResponse | null;
  login: (credentials: LoginRequest) => Promise<void>;
  logout: () => void;
  register: (userData: RegisterRequest) => Promise<void>;
  isAuthenticated: boolean;
}

export const AuthContext = createContext<AuthContextType | undefined>(undefined);

export const AuthProvider: React.FC<{ children: React.ReactNode }> = ({ children }) => {
  const [user, setUser] = useState<AuthResponse | null>(null);

  useEffect(() => {
    const storedUser = storageService.getUser();
    const storedToken = storageService.getAccessToken();
    
    if (storedUser && storedToken) {
      setUser(storedUser);
    }
  }, []);

  const login = useCallback(async (credentials: LoginRequest) => {
    try {
      const response = await authService.login(credentials);
      setUser(response.data);
      storageService.setUser(response.data);
      storageService.setAccessToken(response.data.accessToken);
    } catch (error) {
      console.error("Login failed:", error);
      throw error;
    }
  }, []);

  const register = useCallback(async (userData: RegisterRequest) => {
    try {
      const response = await authService.register(userData);
      setUser(response.data);
      storageService.setUser(response.data);
      storageService.setAccessToken(response.data.accessToken);
    } catch (error) {
      console.error("Registration failed:", error);
      throw error;
    }
  }, []);

  const logout = () => {
    setUser(null);
    storageService.clearAll();
  };

  const isAuthenticated = !!user;

  return (
    <AuthContext.Provider value={{ user, login, logout, register, isAuthenticated }}>
      {children}
    </AuthContext.Provider>
  );
};