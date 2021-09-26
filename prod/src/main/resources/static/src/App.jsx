import React from 'react'
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom'
import Home from './routes/Home'
import Signin from './routes/Signin'
import SignupUser from './routes/SignupUser'
import SignupOrg from './routes/SignupOrg'
import ForgetPassword from './routes/ForgetPassword'
import Feed from './routes/Feed'
import Post from './routes/Post'
import CreatePost from './routes/CreatePost'
import User from './routes/User'
import Org from './routes/Org'
import EditProfile from './routes/EditProfile'
import Signout from './routes/Signout'
import Purpose from './routes/Purpose'
import TopCauses from './routes/TopCauses'
import YourCauses from './routes/YourCauses'
import CreateCause from './routes/CreateCause'
import Footer from './components/Footer'
import { useState } from 'react'
import axios from 'axios'

const App = () => {

    //determine if a user is logged in
    const [userData, setUserData] = useState("")

        
        

        const email = localStorage.getItem('email')
        const pass = localStorage.getItem('password')
        const userId = localStorage.getItem('userId')
        const name = localStorage.getItem('name')

        console.log(email, pass, userId, name)
        if(email && pass && !userId && !name){
            
            axios.get('http://localhost:8080/api/users').then(res => {
            setUserData(res.data);
        })
        
        
        
            for (let user in userData) {
                if (userData[user].emailAddress === email) {
                    localStorage.setItem('userId', userData[user].userId)
                    localStorage.setItem('name', userData[user].name)
                    break
                }
            }
        } 

        

    

    return (
        <div>
        <Router>
            <Switch>

                <Route exact path="/" component={Home}/>
                <Route exact path="/sign-in" component={Signin}/>

                
                <Route exact path="/sign-up-user" component={SignupUser}/>
                <Route exact path="/sign-up-org" component={SignupOrg}/>
                <Route exact path="/forget-password" component={ForgetPassword}/>

                {/* feed is based on a filter of all 
                content specific to the user who logged in */}
                <Route exact path="/feed" component={Feed}/>
                {/*post id */}
                <Route exact path="/post/:id" component={Post}/>

                {/*user id for who's creating the post*/}

                <Route exact path="/create-post/" component={CreatePost}/>
                <Route exact path="/create-cause/" component={CreateCause}/>

                <Route exact path="/user/:id" component={User}/>
                <Route exact path="/org/:id" component={Org}/>

                {/* obviously for these 
                confirm that the account is logged in before proceeding with editing */}
                
                

                
                
                <Route exact path="/edit-profile" component={EditProfile}/>
                <Route exact path="/sign-out" component={Signout}/>
                
                

                <Route exact path="/top-causes" component={TopCauses}/>
                <Route exact path="/your-causes" component={YourCauses}/>

                <Route path="/purpose" component={Purpose}/>

            </Switch>
        </Router>
        <Footer />
        </div>
    )
}

export default App