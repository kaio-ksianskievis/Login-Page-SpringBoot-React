import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import { toast } from 'react-toastify';
import './AuthStyles.css';

function Verify() {
  const navigate = useNavigate();
  const [code, setCode] = useState("");
  const [isLoading, setIsLoading] = useState(false);

  const envia = async (e) => {
    e.preventDefault();
    setIsLoading(true);
    try {
      const response = await axios.post("http://localhost:8080/verify", { code });
      toast.info(response.data); // Mensagem de sucesso da sua API
      navigate("/login");
    } catch (erro) {
      const msg = erro.response?.data?.error || "Código de verificação inválido.";
      toast.error(Array.isArray(msg) ? msg[0].message : msg);
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <div className="auth-container">
      <h2>Verificar Código</h2>
      <p style={{color: '#a0a0a0', fontSize: '0.9rem', marginBottom: '1rem'}}>
        Insira o código enviado para o seu e-mail para ativar sua conta.
      </p>
      <form className="auth-form" onSubmit={envia}>
        <input 
          type='text' 
          placeholder='000000' 
          onChange={(e) => setCode(e.target.value)} 
          className="verify-input"
          required 
        />
        <button className="auth-button" type='submit' disabled={isLoading}>
          {isLoading ? "Validando..." : "Verificar"}
        </button>
      </form>
    </div>
  );
}

export default Verify;