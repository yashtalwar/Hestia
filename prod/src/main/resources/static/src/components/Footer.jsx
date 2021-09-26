import React from 'react'
import { Navbar, Container } from 'react-bootstrap'

const Footer = () => {
    return (
        <div >

            
    
             <Navbar bg="light"  fixed= "bottom" className="footer" >
                 <Container className = "justify-content-center">
                 <Navbar.Brand>Project developed by <a href="http://www.linkedin.com/in/salil-khanna">Salil Khanna</a>, 
                 <a href="https://www.linkedin.com/in/yashtalwar"> Yash Talwar</a>, 
                 <a href="https://www.linkedin.com/in/sarthak-g/"> Sarthak Gupta</a>, and 
                 <a href="https://www.linkedin.com/in/rubin-peci/"> Rubin Peci</a>, Sept 2021 for Women Creating Change and JPMC Code for Good. </Navbar.Brand>
                 </Container>
             </Navbar>
        </div>
    )
}

export default Footer
