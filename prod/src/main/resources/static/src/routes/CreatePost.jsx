import React from 'react'
import HeaderSearch from '../components/HeaderSearch'
import { useState } from 'react';
import { toast, ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import axios from 'axios';


const CreatePost = () => {

    const [title, setTitle] = useState('');
    const [content, setContent] = useState("")
    


    const handleSubmit = (e) => {
        e.preventDefault()

        if (content === "" || title === "") {
            return toast.error("Please fill out all fields")
        }
        else {
            const name = localStorage.getItem("userId");
            // var currentdate = new Date();
            // var datetime = "Last Sync: " + currentdate.getDate() + "/"
            //     + (currentdate.getMonth() + 1) + "/"
            //     + currentdate.getFullYear() + " @ "
            //     + currentdate.getHours() + ":"
            //     + currentdate.getMinutes() + ":"
            //     + currentdate.getSeconds();
            
            let post1 = {
                "content": content, "title": title, "authorId": name, 
            };
            axios.post('http://localhost:8080/api/posts', post1).then(res => {
                console.log(res);
                console.log(res.data);
            })

            window.location.href = "/feed"
        }
    }

    return (
        <div>
            <HeaderSearch />

            {/* create a sign up form for an account */}
            <div className="container">
                <div className="row">
                    <div className="col-sm-9 col-md-7 col-lg-5 mx-auto">
                        <div className="card card-signin my-5">
                            <div className="card-body">
                                <h3 className="card-title text-center">Create Post</h3>
                                <form className="form-signin">
                                    <div className="form-label-group">
                                        <label htmlFor="inputPost">Title </label>
                                        <input value={title} onChange={(e) => setTitle(e.target.value)} type="text" id="inputPost"
                                            className="form-control" placeholder="Enter title here" required autoFocus />
                                        <label htmlFor="blank">    </label>

                                    </div>

                                </form>

                                <form className="form-signin">
                                    <div className="form-label-group">
                                        <label htmlFor="inputPost">Type Content </label>
                                        <input value={content} onChange={(e) => setContent(e.target.value)} type="text" id="inputPost"
                                            className="form-control" placeholder="Enter content here" required autoFocus />
                                        <label htmlFor="blank">    </label>

                                    </div>

                                </form>

                                <ToastContainer
                                    position="top-center"
                                    autoClose={5000}
                                    hideProgressBar={false}
                                    newestOnTop={false}
                                    closeOnClick
                                    rtl={false}
                                    pauseOnFocusLoss
                                    draggable
                                    pauseOnHover={false}
                                />



                                <button className="btn btn-lg btn-primary btn-block text-uppercase" type="submit" onClick={handleSubmit}>Submit Post</button>
                                <hr className="my-4" />
                            </div>

                        </div>

                    </div>
                </div>
            </div>

        </div>
    )
}

export default CreatePost
