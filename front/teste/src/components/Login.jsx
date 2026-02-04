import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import axios from 'axios';
import { toast } from 'react-toastify';
import './AuthStyles.css';

function Login() {
  const navigate = useNavigate();
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [isLoading, setIsLoading] = useState(false);

  const envia = async (e) => {
    e.preventDefault();
    setIsLoading(true);
    try {
      const response = await axios.post("http://localhost:8080/login", { email, password });
      
      // Salva o token retornado pela sua API JWT
      localStorage.setItem('token', response.data.token); 
      
      toast.success("Bem-vindo de volta!");
      navigate("/home");
    } catch (erro) {
      const msg = erro.response?.data?.error || "E-mail ou senha incorretos.";
      toast.error(Array.isArray(msg) ? msg[0].message : msg);
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <div className="auth-container">
      <h2>Entrar</h2>
      <form className="auth-form" onSubmit={envia}>
        <input type='email' placeholder='E-mail' onChange={(e) => setEmail(e.target.value)} required />
        <input type='password' placeholder='Senha' onChange={(e) => setPassword(e.target.value)} required />
        <button className="auth-button" type='submit' disabled={isLoading}>
          {isLoading ? "Carregando..." : "Entrar"}
        </button>
      </form>
      <Link className="auth-link" to='/signup'><p>Não tem uma conta? Clique aqui!</p></Link>
    </div>
  );
}

export default Login;