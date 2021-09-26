import React from 'react'
import { useState } from 'react';

import Navbar from 'react-bootstrap/Navbar'
import { Container, Nav, NavDropdown, Form, FormControl, Button  } from 'react-bootstrap'
import miniLogo from '../img/miniLogo.png'

const HeaderSearch = () => {


    
    const [search, setSearch] = useState("Search Field")

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

                    <div>
                        <select
                            value={search}
                            onChange={(e) => setSearch(e.target.value)}
                            id="rating"
                            className="custom-select"
                        >
                            <option disabled>Search Field</option>
                            <option value="fullname">Full Name</option>
                            <option value="orgname">Organization Name</option>
                            <option value="cause">Cause</option>
                            <option value="location">Location</option>
                            {/* make it a calender*/}
                            <option value="date">Date</option>

                        </select>
                    </div>
                    <Form className="d-flex">
                        <FormControl
                            type="search"
                            placeholder="Search"
                            className="mr-2"
                            aria-label="Search"
                        />
                        <Button variant="outline-success">Search</Button>
                    </Form>

                    <Navbar.Toggle aria-controls="basic-navbar-nav" />
                    <Navbar.Collapse id="basic-navbar-nav" className="justify-content-end">
                        <Nav className="me-auto">

                            <NavDropdown title="Menu" id="basic-nav-dropdown" >
                                <NavDropdown.Item href="/">Home</NavDropdown.Item>
                                <NavDropdown.Item href="/feed">Feed</NavDropdown.Item>
                                <NavDropdown.Item href="/your-causes">Your Causes</NavDropdown.Item>
                                <NavDropdown.Item href="/top-causes">Top Causes</NavDropdown.Item>
                                <NavDropdown.Item href="/purpose">Purpose</NavDropdown.Item>
                                <NavDropdown.Divider />
                                <NavDropdown.Item > Signed in as {localStorage.getItem("name")} </NavDropdown.Item>
                                <NavDropdown.Item href="/" onClick={(e) => {
                                    localStorage.removeItem("email");
                                    localStorage.removeItem("password")
                                    localStorage.removeItem("userId")
                                    localStorage.removeItem("name")
                                }}> Sign Out </NavDropdown.Item>
                                <NavDropdown.Item href="/edit-profile" > Edit Profile</NavDropdown.Item>
                            </NavDropdown>


                        </Nav>
                    </Navbar.Collapse>
                </Container>
            </Navbar>
            <br />
            <br />
            <br />
        </div>

    )
}

export default HeaderSearch
