import React, {useEffect, useState} from "react";

import Modal from "react-bootstrap/Modal";
import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";

const BookForm = ({show, close, addBook, updating, updateBook, currentBook}) => {
    const [book, setBook] = useState({})

    useEffect(() => {
        setBook(currentBook)
    }, [currentBook])

    return (
        <Modal centered show={show} onHide={close}>
            <Modal.Header closeButton>
                {updating &&
                <Modal.Title>Edit book</Modal.Title>
                }
                {!updating &&
                <Modal.Title>Add book</Modal.Title>
                }
            </Modal.Header>
            <Modal.Body>
                <Form.Group >
                    <Form.Label>ISBN: </Form.Label>
                    <Form.Control
                      type="text"
                      onChange={(e) => setBook({...book, isbn: e.target.value})}
                      value={book.isbn}
                    />
                </Form.Group>

                <Form.Group >
                    <Form.Label>Title: </Form.Label>
                    <Form.Control
                      type="text"
                      onChange={(e) => setBook({...book, title: e.target.value})}
                      value={book.title}
                    />
                </Form.Group>

                <Form.Group >
                    <Form.Label>Description: </Form.Label>
                    <Form.Control
                      type="text"
                      onChange={(e) => setBook({...book, description: e.target.value})}
                      value={book.description}
                    />
                </Form.Group>

                <Form.Group >
                    <Form.Label>Image URL: </Form.Label>
                    <Form.Control
                      type="text"
                      onChange={(e) => setBook({...book, imageUrl: e.target.value})}
                      value={book.author}
                    />
                </Form.Group>

                <Form.Group >
                    <Form.Label>Language: </Form.Label>
                    <Form.Control
                      type="text"
                      onChange={(e) => setBook({...book, language: e.target.value})}
                      value={book.language}
                    />
                </Form.Group>

                <Form.Group >
                    <Form.Label>Number of pages: </Form.Label>
                    <Form.Control
                      type="number"
                      onChange={(e) => setBook({...book, numPages: e.target.value})}
                      value={book.pages}
                    />
                </Form.Group>

                <Form.Group >
                    <Form.Label>Publisher: </Form.Label>
                    <Form.Control
                      type="text"
                      onChange={(e) => setBook({...book, publisher: e.target.value})}
                      value={book.publisher}
                    />
                </Form.Group>
            </Modal.Body>

            <Modal.Footer>
                <Button variant="secondary" onClick={close}>
                    Cancel
                </Button>
                {updating &&
                <Button variant="primary" type='submit' onClick={() => updateBook(book)}>
                    Save
                </Button>
                }
                {!updating &&
                <Button variant="primary" type='submit' onClick={() => addBook(book)}>
                    Create
                </Button>
                }
            </Modal.Footer>
        </Modal>
    )
}

export default BookForm