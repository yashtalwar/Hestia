import React from 'react'
import Header from '../components/Header'
import HomeInfo from '../components/HomeInfo'
import {useEffect} from 'react'

const Home = () => {
    
    useEffect(() => {
        //check if email and password are already in local storage
        if(localStorage.getItem("email") && localStorage.getItem("password")){
            
            window.location.href = "/feed"
        }
    }, [])
    return (
        <div >
            <Header />
            <HomeInfo />
        </div>
    )
}

export default Home
