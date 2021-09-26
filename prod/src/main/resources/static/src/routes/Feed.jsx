import React from 'react'
import HeaderSearch from '../components/HeaderSearch'
import axios from 'axios'
import { useState } from 'react'

const Feed = () => {



    const [posts, setPosts] = useState([1,2,3])
    
    const email = localStorage.getItem('email')
    if (email) {
        axios.get('http://localhost:8080/api/posts').then(res => {
            setPosts(res.data);
        })
    } else {
        window.location.href = "/"

    }

    const findUsername = (id) => {
        axios.get(`http://localhost:8080/api/users/${id}`).then(res => {
            return res["data"]["name"]
        })
    }


    return (
        <div>
            <HeaderSearch />
            


            <div className="row fixed-action-button justify-content-center">
                <div className="col-md-12" >
                    <button className="btn btn-primary btn-lg btn-block" onClick={(e) => {
                    e.preventDefault();
                    window.location.href='/create-post';
                    }}>Create Post</button>
                </div>
            </div>


            {/* create four containers filled with text in a square shape */}
            
            {
            posts.constructor === Array &&
            <div className="row">
                { posts.map(post => (
                    <div className="col-md-3" key={post.postId}>
                        <div className="card">
                            <div className="card-body">
                                <h5 className="card-title">{post.title}</h5>

                                

                                <p className="card-text">{post.content} by {findUsername(post["authorId"])}</p>
                                <a href="/feed" className="btn btn-primary">Read More</a>
                            </div>
                        </div>
                    </div>
                ))
                }
            
                
            </div>
            }
            



       

        </div>
    )
}

export default Feed
