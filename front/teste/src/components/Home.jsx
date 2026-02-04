import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import { toast } from 'react-toastify';
import './AuthStyles.css';

function Home() {
    const navigate = useNavigate();
    const [nome, setNome] = useState("");
    const [isLoading, setIsLoading] = useState(true);

    useEffect(() => {
        const buscaDadosUsuario = async () => {
            try {
                const token = localStorage.getItem('token'); // Recupera o token JWT

                if (!token) {
                    navigate("/login");
                    return;
                }

                // Requisição GET para buscar os dados do usuário logado
                const response = await axios.get("http://localhost:8080/home", {
                    headers: { 'Authorization': `Bearer ${token}` }
                });

                setNome(response.data.nome);
            } catch (erro) {
                toast.error("Sessão expirada. Faça login novamente.");
                localStorage.removeItem('token');
                navigate("/login");
            } finally {
                setIsLoading(false);
            }
        };

        buscaDadosUsuario();
    }, [navigate]);

    const handleLogout = () => {
        localStorage.removeItem('token'); // Limpa o token para deslogar
        navigate("/login");
        toast.info("Você saiu do sistema.");
    };

    if (isLoading) {
        return <div className="auth-container"><h2>Carregando...</h2></div>;
    }

    return (
        <div className="auth-container">
            <h2>Bem-vindo, {nome}!</h2>
            
            <div className="home-content" style={{ marginTop: '2rem' }}>
                <button 
                    onClick={handleLogout}
                    className="auth-button"
                    style={{ backgroundColor: '#ff4d4d', color: 'white' }}
                >
                    Sair da Conta
                </button>
            </div>
        </div>
    );
}

export default Home;