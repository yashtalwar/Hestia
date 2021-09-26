import React from 'react'
import Header from '../components/Header'
import {useState} from 'react'
import { toast, ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

const ForgetPassword = () => {

    const [email, setEmail] = useState("")

    const handleSubmit = (e) => {
        e.preventDefault()
        if(email === ""){
            toast.error("Please fill all the fields")
        } else if (!email.includes("@") || !email.includes(".")) {
            return toast.error("Please enter a valid email")
        } else {
            return toast.success("Please check your email to reset your password.")
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
                                        <i className="fas fa-lock" /> Forgot Password?
                                    </span>
                                </h1>
                                

                                <h5 style={{textAlign: 'center'}}> <a href="/forget-password">Enter your email to receive a link to reset your password. </a> </h5>
                                
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
                                        Reset
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

export default ForgetPassword
