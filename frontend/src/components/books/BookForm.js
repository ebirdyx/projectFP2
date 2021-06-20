import React, {useEffect, useState} from "react";

import Modal from "react-bootstrap/Modal";
import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";

const BookForm = ({show, close, addBook, updating, updateBook, currentBook}) => {
    const [book, setBook] = useState({})
    const [selectedFile, setSelectedFile] = useState(null);

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
                    <Form.Label>Year of publication: </Form.Label>
                    <Form.Control
                      type="text"
                      onChange={(e) => setBook({...book, yearOfPublication: e.target.value})}
                      value={book.yearOfPublication}
                    />
                </Form.Group>

                <Form.Group >
                    <Form.Label>Description: </Form.Label>
                    <Form.Control
                      as="textarea"
                      onChange={(e) => setBook({...book, description: e.target.value})}
                      value={book.description}
                    />
                </Form.Group>

                <Form.Group >
                    <Form.Label>Image URL: </Form.Label>
                    <Form.Control
                      type="text"
                      onChange={(e) => setBook({...book, imageUrl: e.target.value})}
                      value={book.imageUrl}
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
                      value={book.numPages}
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

                <Form.Group >
                    <Form.Label>
                        {book.fileName ?
                            <span>Uploaded: {book.fileName}</span> :
                            <span>Upload: </span>
                        }
                    </Form.Label>
                    <Form.Control
                      className="mt-2"
                      type="file"
                      onChange={(e) => setSelectedFile(e.target.files[0])}
                    />
                </Form.Group>
            </Modal.Body>

            <Modal.Footer>
                <Button variant="secondary" onClick={close}>
                    Cancel
                </Button>
                {updating &&
                <Button variant="primary" type='submit' onClick={() => updateBook(book, selectedFile)}>
                    Save
                </Button>
                }
                {!updating &&
                <Button variant="primary" type='submit' onClick={() => addBook(book, selectedFile)}>
                    Create
                </Button>
                }
            </Modal.Footer>
        </Modal>
    )
}

export default BookForm