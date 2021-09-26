import React from 'react'
import Header from '../components/Header'
import { useState } from 'react';
import { toast, ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import axios from "axios";



const SignupOrg = () => {

    const [orgname, setOrgname] = useState("")
    const [password, setPassword] = useState("")
    const [confirm, setConfirm] = useState("")
    const [email, setEmail] = useState("")
    const [number, setNumber] = useState("")
    const [location, setLocation] = useState("Choose Borough")

 

    const handleSubmit = (e) => {
        e.preventDefault()
        const arrNum = number.split("-")
        if (arrNum.length !== 3) {
            return toast.error("Please enter a valid phone number")
        }

        if (orgname === "" || password === "" || confirm === "" || email === "" || number === "" || location === "Choose Borough") {
            return toast.error("Please fill out all fields")
        } else if (password !== confirm) {
            return toast.error("Passwords do not match")
        } else if (!email.includes("@") || !email.includes(".")) {
            return toast.error("Please enter a valid email")
        } else if (!(arrNum[0].length === 3 && arrNum[1].length === 3 && arrNum[2].length === 4 ) ) {
            return toast.error("Please enter a valid phone number")
        } else {
            console.log(orgname, password, email, number, location)
            let org1 = {"name": orgname,"password":password,"emailAddress": email, "phoneNumber":number,"location":location.toUpperCase()};
            axios.post('http://localhost:8080/api/users',org1).then(res => {
                console.log(res);
                console.log(res.data);
            })
            //store email and password in local storage
            localStorage.setItem("email", email)
            localStorage.setItem("password", password)
            window.location.href = "/feed"
            
        }
    }

    return (
        <div>
            <Header />

            {/* create a sign up form for an account */}
            <div className="container">
                <div className="row">
                    <div className="col-sm-9 col-md-7 col-lg-5 mx-auto">
                        <div className="card card-signin my-5">
                            <div className="card-body">
                                <h3 className="card-title text-center">Sign Up</h3>
                                <form className="form-signin">
                                    <div className="form-label-group">
                                    <label htmlFor="inputUsername">Organization Name</label>
                                        <input value={orgname} onChange={(e) => setOrgname(e.target.value)} type="text" id="inputUsername" 
                                        className="form-control" placeholder="(ex: Code for Good)" required autoFocus />
                                        <label htmlFor="blank">    </label>
                                        
                                    </div>


                                    <div className="form-label-group">
                                    <label htmlFor="phoneNumber">Phone Number</label>
                                        <input value={number} onChange={(e) => setNumber(e.target.value)} 
                                        type="phoneNumber" id="inputNumber" className="form-control" placeholder="(ex: 817-365-8906)" required />
                                        <label htmlFor="blank">    </label>

                                    </div>

                                    <div className="form-label-group">
                                    <label htmlFor="inputEmail">Email address</label>
                                        <input value={email} onChange={(e) => setEmail(e.target.value)} 
                                        type="email" id="inputEmail" className="form-control" placeholder="(ex: jpmc@gmail.com)" required />
                                        <label htmlFor="blank">    </label>

                                    </div>

                                    <div className="form-label-group">
                                        <label htmlFor="inputPassword">Password</label>
                                        <input value={password} onChange={(e) => setPassword(e.target.value)} 
                                        type="password" id="inputPassword" className="form-control" placeholder="Password" required />
                                        <label htmlFor="blank">    </label>

                                    </div>

                                    <div className="form-label-group">
                                        <label htmlFor="inputPassword">Confirm Password</label>
                                        <input value={confirm} onChange={(e) => setConfirm(e.target.value)} 
                                        type="password" id="inputPassword" className="form-control" placeholder="Confirm Password" required />
                                        <label htmlFor="blank">    </label>

                                    </div>



                                    <div className="form-label-group">
                                        <label htmlFor="text">Location</label>
                                        <select
                                            value={location}
                                            onChange={(e) => setLocation(e.target.value)}
                                            id="location"
                                            className="custom-select"
                                            >
                                            <option disabled>Choose Borough</option>
                                            <option value="Manhattan">Manhattan</option>
                                            <option value="Queens">Queens</option>
                                            <option value="Brooklyn">Brooklyn</option>
                                            <option value="Bronx">Bronx</option>
                                            <option value="Staten Island">Staten Island</option>
                                            <option value="Other">Other</option>
                                        </select>
                                        
                                        <label htmlFor="blank">    </label>
                                        <br></br>
                                        <br></br>

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

            

                                <button className="btn btn-lg btn-primary btn-block text-uppercase" type = "submit" onClick={handleSubmit}>Sign up</button>
                                    <hr className="my-4" />
                            </div>

                        </div>
                        
                    </div>
                </div>
            </div>

        </div>
    )
}

export default SignupOrg
