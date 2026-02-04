
import './App.css'
import Login from './components/Login'
import { BrowserRouter, Navigate, Route, Routes } from 'react-router-dom'
import Signup from './components/Signup'
import Verify from './components/Verify'
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import Home from './components/Home'

function App() {
  
  return (
      <div>
        <BrowserRouter>
          <Routes>
            <Route path='/' element={<Navigate to="/login" replace/>}/>
            <Route path='/login' element={<Login/>}/>
            <Route path='/home' element={<Home/>}/>
            <Route path='/signup' element={<Signup/>}/>
            <Route path='/verify' element={<Verify/>}/>
          </Routes>
        </BrowserRouter>
        <ToastContainer theme="dark" position="top-right" />
      </div>

  )
}

export default App
