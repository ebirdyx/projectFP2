import React, {useEffect, useState} from "react";

import Modal from "react-bootstrap/Modal";
import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";

const BookForm = ({show, close, addBook, updating, updateBook, currentBook}) => {
    const [book, setBook] = useState({})

    const handleChangeTitle = (event) => {
        setBook({...book, title: event.target.value})
    }

    const handleChangeDescription = (event) => {
        setBook({...book, description: event.target.value})
    }

    const handleChangeAuthor = (event) => {
        setBook({...book, author: event.target.value})
    }

    const handleChangeLanguage = (event) => {
        setBook({...book, language: event.target.value})
    }

    const handleChangePages = (event) => {
        setBook({...book, pages: event.target.value})
    }

    const handleChangePublisher = (event) => {
        setBook({...book, publisher: event.target.value})
    }

    const handleChangeIsbn = (event) => {
        setBook({...book, isbn: event.target.value})
    }

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
                    <Form.Control type="text" onChange={handleChangeIsbn} value={book.isbn} />
                </Form.Group>

                <Form.Group >
                    <Form.Label>Title: </Form.Label>
                    <Form.Control type="text" onChange={handleChangeTitle} value={book.title} />
                </Form.Group>

                <Form.Group >
                    <Form.Label>Description: </Form.Label>
                    <Form.Control type="text" onChange={handleChangeDescription} value={book.description} />
                </Form.Group>

                <Form.Group >
                    <Form.Label>Author: </Form.Label>
                    <Form.Control type="text" onChange={handleChangeAuthor} value={book.author} />
                </Form.Group>

                <Form.Group >
                    <Form.Label>Language: </Form.Label>
                    <Form.Control type="text" onChange={handleChangeLanguage} value={book.language} />
                </Form.Group>

                <Form.Group >
                    <Form.Label>Pages: </Form.Label>
                    <Form.Control type="number" onChange={handleChangePages} value={book.pages} />
                </Form.Group>

                <Form.Group >
                    <Form.Label>Publisher: </Form.Label>
                    <Form.Control type="text" onChange={handleChangePublisher} value={book.publisher} />
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