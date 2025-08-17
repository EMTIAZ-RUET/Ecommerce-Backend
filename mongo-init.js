// MongoDB initialization script
db = db.getSiblingDB('ecommerce');

// Create collections for product service
db.createCollection('products');
db.createCollection('categories');

// Create indexes for better performance
db.products.createIndex({ "name": "text", "description": "text" });
db.products.createIndex({ "categoryId": 1 });
db.products.createIndex({ "sku": 1 }, { unique: true });
db.products.createIndex({ "active": 1 });
db.products.createIndex({ "price": 1 });

// Insert sample categories
db.categories.insertMany([
    {
        "_id": ObjectId(),
        "name": "Electronics",
        "description": "Electronic devices and gadgets",
        "active": true,
        "createdAt": new Date(),
        "updatedAt": new Date()
    },
    {
        "_id": ObjectId(),
        "name": "Clothing",
        "description": "Fashion and apparel",
        "active": true,
        "createdAt": new Date(),
        "updatedAt": new Date()
    },
    {
        "_id": ObjectId(),
        "name": "Books",
        "description": "Books and literature",
        "active": true,
        "createdAt": new Date(),
        "updatedAt": new Date()
    }
]);

print('MongoDB initialization completed successfully');
