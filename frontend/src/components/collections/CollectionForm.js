import React, {useEffect, useState} from "react";

import Modal from "react-bootstrap/Modal";
import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";

const CollectionForm = ({show, close, addCollection, updating, updateCollection, currentCollection}) => {
    const [collection, setCollection] = useState({})

    const handleChangeName = (event) => {
        setCollection({...collection, name: event.target.value})
    }

    const handleChangeDescription = (event) => {
        setCollection({...collection, description: event.target.value})
    }


    useEffect(() => {
        setCollection(currentCollection)
    }, [currentCollection])

    return (
        <Modal centered show={show} onHide={close}>
            <Modal.Header closeButton>
                {updating &&
                <Modal.Title>Edit collection</Modal.Title>
                }
                {!updating &&
                <Modal.Title>Add Collection</Modal.Title>
                }
            </Modal.Header>
            <Modal.Body>
                <Form.Group >
                    <Form.Label>Name: </Form.Label>
                    <Form.Control type="text" onChange={handleChangeName} value={collection.title} />
                </Form.Group>

                <Form.Group >
                    <Form.Label>Description: </Form.Label>
                    <Form.Control type="text" onChange={handleChangeDescription} value={collection.description} />
                </Form.Group>

            </Modal.Body>

            <Modal.Footer>
                <Button variant="secondary" onClick={close}>
                    Cancel
                </Button>
                {updating &&
                <Button variant="primary" type='submit' onClick={() => updateCollection(collection)}>
                    Save
                </Button>
                }
                {!updating &&
                <Button variant="primary" type='submit' onClick={() => addCollection(collection)}>
                    Create
                </Button>
                }
            </Modal.Footer>
        </Modal>
    )
}

export default CollectionForm