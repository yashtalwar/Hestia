import React from 'react'
import Header from '../components/Header'
import HeaderSearch from '../components/HeaderSearch'
import headerLogo from '../img/headerLogo.png'
import community from '../img/community.png'


const Purpose = () => {
    const id = localStorage.getItem('userId')
    var userSignedIn = false;
    if (id) {
        userSignedIn = true;
    }
    return (
        <div>
            {/* Depending on if there is a user signed in*/}
            { userSignedIn ? 
                <HeaderSearch /> 
                : <Header /> 
            }
            
            {/* container filled with text alternating between name and images */}
            <div className="container">
                <div className="row">
                    <div className="col-md-12">
                        <h1 className = "text-center" style= {{marginTop: '1rem', marginBottom: '1rem'}}>Purpose</h1>

                        {/* create a container with text on the left and image on the right */}
                        <div className="row">
                            <div className="col-md-6">
                                <h4>
                                    <b>Mission</b>: Hestia’s mission is to foster a virtual environment to 
                                    encourage discussion and provide an axiom to shift power to women who have 
                                    been historically isolated from civil activities.                                 
                                </h4>

                            </div>
                            <div className="col-md-6">
                                <img src={headerLogo} 
                                style={{
                                    display: 'block',
                                    width: '80%',
                                    height: 'auto',
                                    marginTop: '20px',
                                    marginBottom: '20px'
                                }}
                                alt="Purpose" className="img-fluid" />
                            </div>
                        </div>
                    
                        <div className="row">
                            <div className="col-md-6">
                                <h4>
                                    <b>Product</b>: A comprehensive web application that 
                                    allows anyone who identifies as a woman to connect with 
                                    others users through topic specific forums in order to gauge more civic engagement.                                 
                                </h4>

                            </div>
                            <div className="col-md-6">
                                <img src={community} 
                                style={{
                                    display: 'block',
                                    width: '70%',
                                    height: 'auto',
                                    marginTop: '20px',
                                    marginBottom: '20px'
                                }}
                                alt="Purpose" className="img-fluid" />
                            </div>
                        </div>


                        
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Purpose
