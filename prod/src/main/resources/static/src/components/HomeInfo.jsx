import React from 'react'
import headerLogo from '../img/headerLogo.png'

const HomeInfo = () => {
    return (
        <div className = "container">
            <img
                src={headerLogo}
                
                style={{
                    display: 'block',
                    marginLeft: 'auto',
                    marginRight: 'auto',
                    width: '70%',
                    height: 'auto',
                    marginTop: '20px',
                    marginBottom: '20px'
                }}
                
                className="text-center"
                alt="Hestia logo main"
                />




            <div className="row">
                <div className="col-sm-4" style = {{marginTop: '1rem'}}>
                    <button type="button" className="btn btn-primary btn-lg btn-block" onClick={(e) => {
                    e.preventDefault();
                    window.location.href='/sign-in';
                    }}>
                        <a href="/sign-in" style={{color: 'white'}}>Sign In</a>
                    </button>
                </div>
                <div className="col-sm-4" style = {{marginTop: '1rem'}} onClick={(e) => {
                    e.preventDefault();
                    window.location.href='/sign-up-user';
                    }}>
                    <button type="button" className="btn btn-primary btn-lg btn-block" href="/sign-up-user">
                        <a href="/sign-up-user" style={{color: 'white'}}>Sign Up as User</a>
                    </button>
                </div>
                <div className="col-sm-4" style = {{marginTop: '1rem'}} onClick={(e) => {
                    e.preventDefault();
                    window.location.href='/sign-up-org';
                    }}>
                    <button type="button" className="btn btn-primary btn-lg btn-block" href="/sign-up-org">
                        <a href="/sign-up-org" style={{color: 'white'}} >Sign Up as Organization</a>
                    </button>
                </div>
            </div>

        </div>
    )
}

export default HomeInfo
