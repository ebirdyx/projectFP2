import React, {useEffect, useState} from "react";

import Modal from "react-bootstrap/Modal";
import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";

const ProductForm = ({show, close, addProduct, updating, updateProduct, currentProduct}) => {
  const [product, setProduct] = useState({})

  const handleChangeName = (event) => {
    setProduct({...product, name: event.target.value})
  }

  const handleChangeDescription = (event) => {
    setProduct({...product, description: event.target.value})
  }

  const handleChangePrice = (event) => {
    setProduct({...product, price: event.target.value})
  }

  useEffect(() => {
    setProduct(currentProduct)
  }, [currentProduct])

  return (
    <Modal centered show={show} onHide={close}>
      <Modal.Header closeButton>
        {updating &&
        <Modal.Title>Edit product</Modal.Title>
        }
        {!updating &&
        <Modal.Title>Add product</Modal.Title>
        }
      </Modal.Header>
      <Modal.Body>
        <Form.Group >
          <Form.Label>Name: </Form.Label>
          <Form.Control type="text" onChange={handleChangeName} value={product.name} />
        </Form.Group>

        <Form.Group >
          <Form.Label>Description: </Form.Label>
          <Form.Control type="text" onChange={handleChangeDescription} value={product.description} />
        </Form.Group>

        <Form.Group >
          <Form.Label>Price: </Form.Label>
          <Form.Control type="number" onChange={handleChangePrice} value={product.price} />
        </Form.Group>
      </Modal.Body>

      <Modal.Footer>
        <Button variant="secondary" onClick={close}>
          Cancel
        </Button>
        {updating &&
        <Button variant="primary" type='submit' onClick={() => updateProduct(product)}>
          Save
        </Button>
        }
        {!updating &&
        <Button variant="primary" type='submit' onClick={() => addProduct(product)}>
          Create
        </Button>
        }
      </Modal.Footer>
    </Modal>
  )
}

export default ProductForm