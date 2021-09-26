import React from 'react'
import Header from '../components/Header'
import {useState, useEffect} from 'react'
import { toast, ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import axios from 'axios';

const Signin = () => {

    const [email, setEmail] = useState("")
    const [password, setPassword] = useState("")

    useEffect(() => {
        //check if email and password are already in local storage
        if(localStorage.getItem("email") && localStorage.getItem("password")){
            
            window.location.href = "/feed"
        }
    }, [])

    const handleSubmit = (e) => {
        e.preventDefault()
        if(email === "" || password === ""){
            toast.error("Please fill all the fields")
        } else if (!email.includes("@") || !email.includes(".")) {
            return toast.error("Please enter a valid email")
        } else {
            let person1 = {"emailAddress": email,"password":password};
            axios.post('http://localhost:8080/api/users/verify',person1).then(res => {
                if (res == null) {
                    return toast.error("User does not exist or invalid login");
                }
                else {
                    localStorage.setItem("email", email)
                    localStorage.setItem("password", password)
            
                    window.location.href = "/feed"
                }
                console.log(res);
                console.log(res.data);
            })
            
        }
    }
        
    return (
        <div>
            <Header />


        
            <div className="container" style= {{marginTop: '1rem'}}>


                <div className="row">
                    <div className="col-md-6 mx-auto">
                        <div className="card">
                            <div className="card-body">
                                <h1 className="text-center pb-4 pt-3">
                                    <span className="text-primary">
                                        <i className="fas fa-lock" /> Sign In
                                    </span>
                                </h1>
                                

                                <h5 style={{textAlign: 'center'}}> <a href="/forget-password">Forget Password? </a> </h5>
                                
                                <form>
                                    <div className="form-group">
                                        <label htmlFor="email">Email</label>
                                        <input
                                            value = {email}
                                            onChange = {(e) => setEmail(e.target.value)}
                                            type="email"
                                            className="form-control"
                                            id="email"
                                            aria-describedby="emailHelp"
                                            placeholder="Enter email"
                                        />
                                        
                                    </div>
                                    <div className="form-group">
                                        <label htmlFor="password">Password</label>
                                        <input 
                                        value = {password}
                                        onChange = {(e) => setPassword(e.target.value)}
                                        type="password"
                                        className="form-control"
                                        id="password"
                                        placeholder="Password"
                                        />
                                    </div>

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

                                    <button type="submit" onClick = {handleSubmit} className="btn btn-primary btn-block">
                                        Submit
                                    </button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        
    
                                    
            
        </div>
    )
}

export default Signin
