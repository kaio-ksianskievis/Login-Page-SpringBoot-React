import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import axios from 'axios';
import { toast } from 'react-toastify';
import './AuthStyles.css';

function Signup() {
  const navigate = useNavigate();
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [nome, setNome] = useState("");
  const [isLoading, setIsLoading] = useState(false);

  const envia = async (e) => {
    e.preventDefault();
    setIsLoading(true);
    try {
      await axios.post("http://localhost:8080/register", {
        email, password, nome, role: "USER"
      });
      toast.success("Conta criada! Verifique seu e-mail para o código.");
      navigate("/verify");
    } catch (erro) {
      const msg = erro.response?.data?.error || "Erro ao criar conta.";
      toast.error(Array.isArray(msg) ? msg[0].message : msg);
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <div className="auth-container">
      <h2>Criar Conta</h2>
      <form className="auth-form" onSubmit={envia}>
        <input type='text' placeholder='Nome Completo' onChange={(e) => setNome(e.target.value)} required />
        <input type='email' placeholder='E-mail' onChange={(e) => setEmail(e.target.value)} required />
        <input type='password' placeholder='Senha' onChange={(e) => setPassword(e.target.value)} required />
        <button className="auth-button" type='submit' disabled={isLoading}>
          {isLoading ? "Enviando..." : "Cadastrar"}
        </button>
      </form>
      <Link className="auth-link" to='/login'><p>Já tem uma conta? Clique aqui!</p></Link>
    </div>
  );
}

export default Signup;