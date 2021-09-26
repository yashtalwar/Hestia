import React from 'react'
import HeaderSearch from '../components/HeaderSearch'

const TopCauses = () => {
    return (
        <div>
            <HeaderSearch />
            <div className="row fixed-action-button justify-content-center">
                <div className="col-md-12" >
                    <button className="btn btn-primary btn-lg btn-block" onClick={(e) => {
                    e.preventDefault();
                    window.location.href='/create-cause';
                    }}>Create Cause</button>
                </div>
            </div>


        </div>
    )
}

export default TopCauses
