import {Modal, Button} from "react-bootstrap";
import BookApi from "../../api/book";
import fileDownload from "js-file-download";

const BookDetails = ({show, close, book}) => {
  const handleDownload = (e) => {
    e.preventDefault()

    BookApi.download(book.id)
      .then(res => fileDownload(res.data, book.fileName))
  }

  return (
    <Modal
      show={show}
      onHide={close}
      centered
    >
      <Modal.Header closeButton>
        <Modal.Title>{book.title}</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <div>
          <div className="text-center mb-xl-5">
            <img src={book.imageUrl} width="300" height="300" />
          </div>
          <div className="text-lg-left mb-2 font-weight-bold">Description: </div>
          <div>
            {book.description}
          </div>
          {book.fileName &&
            <div className="text-center mt-4 mb-4">
              <Button
                variant="success"
                onClick={handleDownload}
              >Download book</Button>
            </div>
          }
        </div>
      </Modal.Body>
      <Modal.Footer>
        <Button variant="primary" onClick={close}>
          Close
        </Button>
      </Modal.Footer>
    </Modal>
  )
}

export default BookDetails