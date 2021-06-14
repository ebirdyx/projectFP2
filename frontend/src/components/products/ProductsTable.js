import React from "react";

import Table from "react-bootstrap/Table";
import Button from "react-bootstrap/Button";

const ProductsTable = ({products, deleteProduct, updateProduct}) => {
  return (
    <Table striped bordered hover size="sm">
      <thead>
      <tr>
        <th>#</th>
        <th>Name</th>
        <th>Description</th>
        <th>Price</th>
        <th>Actions</th>
      </tr>
      </thead>
      <tbody>

      {products.map(product => (
        <tr key={product.id}>
          <td>{product.id}</td>
          <td>{product.name}</td>
          <td>{product.description}</td>
          <td>{product.price}</td>
          <td>
            <Button variant="secondary" onClick={() => updateProduct(product.id)}>Edit</Button>
            <Button variant="danger" onClick={() => deleteProduct(product.id)}>Delete</Button>
          </td>
        </tr>
      ))}
      </tbody>
    </Table>
  )
}

export default ProductsTable