import React, {useEffect, useState} from "react";

import UserApi from "../../api/user";
import Form from "react-bootstrap/Form";

const UserProfile = () => {
  const [userProfile, setUserProfile] = useState({})

  useEffect(() => {
    UserApi.profile()
      .then(res => {
        console.log(res.data)
        setUserProfile(res.data)
      })
      .catch(err => console.log(err))
  }, [])

  return (
    <div className="container mt-xl-5">
      <div className="row">
        <div className="col border">
          <img
            src="https://s3.eu-central-1.amazonaws.com/bootstrapbaymisc/blog/24_days_bootstrap/fox.jpg"
            width="300"
            height="300"
          />
        </div>

        <div className="col border">
          <div className="w-75 p-4">
            <h2 className="mt-lg-4">User Profile</h2>

            <Form.Group className="row mt-lg-5" >
              <Form.Label className="col">First name: </Form.Label>
              <Form.Control
                className="col"
                type="text"
                value={userProfile.firstName}
                disabled
              />
            </Form.Group>

            <Form.Group className="row">
              <Form.Label className="col">Last name: </Form.Label>
              <Form.Control
                className="col"
                type="text"
                value={userProfile.lastName}
                disabled
              />
            </Form.Group>

            <Form.Group className="row">
              <Form.Label className="col">Username: </Form.Label>
              <Form.Control
                className="col"
                type="text"
                value={userProfile.username}
                disabled
              />
            </Form.Group>

            <Form.Group className="row">
              <Form.Label className="col">Email: </Form.Label>
              <Form.Control
                className="col"
                type="text"
                value={userProfile.email}
                disabled
              />
            </Form.Group>
          </div>
        </div>
      </div>
    </div>
  )
}

export default UserProfile