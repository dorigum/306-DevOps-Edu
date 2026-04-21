import React, { useState, useEffect } from 'react';
import { boardApi } from '../../api/boardApi';
import './BoardList.css';

const BoardList = () => {
  const [posts, setPosts] = useState([]);
  const [searchTerm, setSearchTerm] = useState('');
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetchPosts();
  }, []);

  const fetchPosts = async () => {
    try {
      setLoading(true);
      const response = await boardApi.getAll();
      setPosts(response.data);
    } catch (error) {
      console.error('Failed to fetch posts:', error);
    } finally {
      setLoading(false);
    }
  };

  const filteredPosts = Array.isArray(posts) ? posts.filter(post => 
    (post.title?.toLowerCase() || '').includes(searchTerm.toLowerCase()) ||
    (post.author?.toLowerCase() || '').includes(searchTerm.toLowerCase())
  ) : [];

  return (
    <div className="board-list-page">
      <div className="list-header">
        <h1>Recent Feed</h1>
        <div className="search-box glass-panel">
          <input 
            type="text" 
            placeholder="Search by title or author..." 
            value={searchTerm}
            onChange={(e) => setSearchTerm(e.target.value)}
          />
        </div>
      </div>

      {loading ? (
        <div className="loading-state">Loading premium vibes...</div>
      ) : (
        <div className="post-grid">
          {filteredPosts.length > 0 ? (
            filteredPosts.map(post => (
              <div key={post.id} className="post-card glass-panel fade-in">
                <div className="post-id">#{post.id}</div>
                <h2 className="post-title">{post.title}</h2>
                <p className="post-excerpt">{post.content?.substring(0, 100)}...</p>
                <div className="post-footer">
                  <span className="post-author">{post.author}</span>
                  <span className="post-date">{post.created_at ? new Date(post.created_at).toLocaleDateString() : 'Unknown Date'}</span>
                </div>
              </div>
            ))
          ) : (
            <div className="empty-state">No vibes found. Be the first to post!</div>
          )}
        </div>
      )}
    </div>
  );
};

export default BoardList;
