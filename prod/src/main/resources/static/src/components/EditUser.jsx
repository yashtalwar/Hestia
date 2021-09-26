import React from 'react'
import HeaderSearch from '../components/HeaderSearch'
import { useState, useEffect } from 'react';
import { toast, ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import axios from "axios";



const EditUser = ({userInfo}) => {

    const [user, setUser] = useState("")
    const [password, setPassword] = useState("")
    const [email, setEmail] = useState("")
    const [number, setNumber] = useState("")
    const [pronouns, setPronouns] = useState("")
    const [language, setLanguage] = useState("Select Language")
    const [location, setLocation] = useState("Choose Borough")

   
    useEffect(() => {
        
        setUser(userInfo.name)
        setPassword(userInfo.password)
        setEmail(userInfo.emailAddress)
        setNumber(userInfo.phoneNumber)
        setPronouns(userInfo.pronouns)
        setLanguage(userInfo.preferredLanguage)
        setLocation(userInfo.location)
        
    }, [userInfo])

    const handleSubmit = (e) => {
        e.preventDefault()
        const arrNum = number.split("-")
        if (arrNum.length !== 3) {
            return toast.error("Please enter a valid phone number")
        }

        if (!(arrNum[0].length === 3 && arrNum[1].length === 3 && arrNum[2].length === 4 ) ) {
            return toast.error("Please enter a valid phone number")
        } else {
            console.log(user, password, email, number, pronouns, language, location)
            let person1 = {"name": user,"password":password,"emailAddress": email, "phoneNumber":number,"pronouns":pronouns,"location":location.toUpperCase(), "preferredLanguage": language.toUpperCase()};
            axios.put(`http://localhost:8080/api/users/${userInfo.userId}`, person1).then(res => {
                console.log(res);
                console.log(res.data);
            })

            localStorage.setItem("email", email)
            localStorage.setItem("password", password)
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
                                <h3 className="card-title text-center">Edit Profile</h3>
                                <form className="form-signin">
                                    <div className="form-label-group">
                                    <label htmlFor="inputUsername">Name</label>
                                        <input value={user} type="text" id="inputUsername" 
                                        className="form-control" placeholder="(ex: Serena Williams)" required autoFocus />
                                        <label htmlFor="blank">    </label>
                                        
                                    </div>


                                    <div className="form-label-group">
                                    <label htmlFor="phoneNumber">Edit Phone Number</label>
                                        <input value={number} onChange={(e) => setNumber(e.target.value)} 
                                        type="phoneNumber" id="inputNumber" className="form-control" placeholder="(ex: 817-365-8906)" required />
                                        <label htmlFor="blank">    </label>

                                    </div>

                                    <div className="form-label-group">
                                    <label htmlFor="inputEmail">Email address</label>
                                        <input value={email} 
                                        type="email" id="inputEmail" className="form-control" placeholder="(ex: serena@gmail.com)" required />
                                        <label htmlFor="blank">    </label>

                                    </div>



                                    <div className="form-label-group">
                                        <label htmlFor="text">Edit Pronouns</label>
                                        <input value={pronouns} onChange={(e) => setPronouns(e.target.value)} 
                                        type="text" id="text" className="form-control" placeholder="(ex: She/Her)" required />
                                        <label htmlFor="blank">    </label>

                                    </div>

                                    <div className="form-label-group">
                                        <label htmlFor="text">Edit Language</label>
                                        
                                        <select
                                            value={language}
                                            onChange={(e) => setLanguage(e.target.value)}
                                            id="language"
                                            className="custom-select form-control"
                                            >
                                            <option disabled>Select Language</option>
                                            <option value="English">English</option>
                                            <option value="Spanish">Spanish</option>

                                        </select>
                                        
                                        <br></br>
                                        <br></br>

                                    </div>

                                    <div className="form-label-group">
                                        <label htmlFor="text">Edit Location</label>
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

            

                                <button className="btn btn-lg btn-primary btn-block text-uppercase" type = "submit" onClick={handleSubmit}>Save Changes</button>
                                    <hr className="my-4" />
                            </div>

                        </div>
                        
                    </div>
                </div>
            </div>

        </div>
    )
}

export default EditUser

