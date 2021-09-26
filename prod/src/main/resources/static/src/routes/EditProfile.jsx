import React from 'react'
import EditUser from '../components/EditUser'
import EditOrg from '../components/EditOrg'
import { useState } from 'react'
import axios from 'axios'


const EditProfile = () => {

    const [userInfo, setUserInfo] = useState("")
    const id = localStorage.getItem('userId')
    if (id) {
        axios.get(`http://localhost:8080/api/users/${id}`).then(res => {
           setUserInfo(res.data)
        })
    } else {
        window.location.href = "/"

    }
    
    // console.log(userInfo.pronouns)

    const org = userInfo.pronouns == null;

    
    return (
        <div>
            {org ? <EditOrg userInfo = {userInfo} /> : <EditUser userInfo = {userInfo} />}
        </div>
    )
}

export default EditProfile

