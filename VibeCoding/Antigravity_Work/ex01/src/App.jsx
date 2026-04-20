import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Layout from './components/Layout/Layout';
import BoardList from './features/Board/BoardList';
import BoardForm from './features/Board/BoardForm';
import './App.css';

function App() {
  return (
    <Router>
      <Layout>
        <Routes>
          <Route path="/" element={<BoardList />} />
          <Route path="/create" element={<BoardForm />} />
          <Route path="/edit/:id" element={<BoardForm isEdit={true} />} />
        </Routes>
      </Layout>
    </Router>
  );
}

export default App;
