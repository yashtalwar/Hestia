import React from 'react'
import Navbar from 'react-bootstrap/Navbar'
import { Container, Nav } from 'react-bootstrap'
import miniLogo from '../img/miniLogo.png'

const Header = () => {
    return (
        <div>

            <Navbar fixed="top" collapseOnSelect expand="lg" bg="dark" variant="dark">
                <Container>
                <Navbar.Brand href="/">
                    <img
                        src={miniLogo}
                        width="30"
                        height="40"
                        className="d-inline-block align-top"
                        alt="Hestia logo"
                    />
                </Navbar.Brand>
                <Navbar.Brand href="/" className="font-weight-light display-1 align-center"> 
                    <h2> Hestia </h2> 
                </Navbar.Brand>

                <Navbar.Toggle aria-controls="basic-navbar-nav" />
                <Navbar.Collapse id="basic-navbar-nav">
                <Nav className="me-auto">
                    <Nav.Link href="/sign-in">Sign In </Nav.Link>
                    <Nav.Link href="/sign-up-user">Sign Up as User</Nav.Link>
                    <Nav.Link href="/sign-up-org">Sign Up as Organization </Nav.Link>

                </Nav>
                </Navbar.Collapse>

                <Navbar.Collapse id="basic-navbar-nav" className="justify-content-end">
                <Nav className="me-auto">

                    <Nav.Link href="/feed"> <h4> Feed </h4> </Nav.Link>
                    <Nav.Link href="/purpose"> <h4> Purpose </h4> </Nav.Link>


                </Nav>
                </Navbar.Collapse>
            </Container>
            </Navbar>
        <br/>
        <br/>
        <br/>
        </div>
        
    )
}

export default Header
