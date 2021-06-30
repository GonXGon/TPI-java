const MongoClient = require('mongodb').MongoClient;
const assert = require('assert');
// const connect = require("./connect"); 
const connect = require("./connect_atlas_dummy"); // url from connect module
const client = new MongoClient(connect.database.url, { useUnifiedTopology: true } );

const dbName = 'Phone_Database';

// Use connect method to connect to the server
client.connect(function(err) {
  // using the assert module for testing
  assert.equal(null, err);
  console.log("Connected successfully to server");
  // use this database
  const db = client.db(dbName);

  insertDocuments(db, function() {
    console.log("Inserted Personal Data succesfully");
    insertPhone(db, function() {
      console.log("Inserted Phone Data succesfully");
      insertOrder(db,function(){
        console.log("Inserted Order Data succesfully");
        updateDocument(db,function(){
          console.log("Updated Order Data succesfully");
          findDocumentsFiltered(db,function(){
            console.log("joined Data succesfully");
            removeDocument(db,function(){
              console.log("Removed Data succesfully");
              client.close();
            });
          });
        });
      });
    });
  });
       
});


//
// insertDocuments() : insert three documents in to "documents"
//                     collection (created if it does not exist)
//
const insertDocuments = function(db, callback) {
    // Using the "documents" collection
    const collection = db.collection('documents');
    // Insert some documents
    collection.insertMany([
      {"Userid":"1","Title":"Mr","fname":"Shubham","lname":"Banyal","email":"shubham.banyal@purplemail.ie","mobile":"0849937354","DoB":"1998-05-02",
        "Address 1":"123 avenue eufbeu lane dbulin 23","Town":"Dublin","County/City":"Citywest","Eircode":"D2345y65"},

      {"Userid":"2","Title":"Mrs","fname":"Iarlaith","lname":"Kelly","email":"iarlaith.kelly@fuchsiamail.ie","mobile":"0843977120","DoB":"1961-01-11",
        "Address 1":"456 street dublin 24","Town":"Dublin","County/City":"Tallaght","Eircode":"D24Y73D"}, 

      {"Userid":"3","Title":"Ms","fname":"Brigid","lname":"Flynn","email":"brigid.flynn@silvermail.ie","mobile":"0844020733","DoB":"1975-11-05",
        "Address 1":"789 Apartment dublin 1","Town":"Dublin","County/City":"Malahide","Eircode":"D1EX6D"},
    ], function(err, result) {
      // using the assert module for testing
      assert.equal(err, null);
      assert.equal(3, result.result.n);
      assert.equal(3, result.ops.length);
      // all good if we get to here
      console.log("Inserted 3 documents into the collection");
      callback(result);
    });
  }


  const insertPhone = function(db,callback){
    const collection = db.collection('Phone_Details');
    collection.insertMany([{"Phoneid":"1","Manufacturer":"Apple","Model":"Iphone 12","Price":"£1000",
    "Display_Size":"6 inch","Storage":"128GB","Ram":"8GB"},

    {"Phoneid":"2","Manufacturer":"Samsung","Model":"Samsung Galaxy s20","Price":"£1100",
    "Display_Size":"6.5 inch","Storage":"128GB","Ram":"12GB"},

    {"Phoneid":"3","Manufacturer":"Oneplus","Model":"OnePlus 9 Pro","Price":"£900",
    "Display_Size":"6.75 inch","Storage":"128GB","Ram":"8GB"},],function(err,result){
      assert.equal(err, null);
      assert.equal(3, result.result.n);
      assert.equal(3, result.ops.length);
      // all good if we get to here
      console.log("Inserted 3 Phone_Details into the collection");
      callback(result);
    });
  }



  const insertOrder = function(db,callback){
    const collection = db.collection('Order_details');
    collection.insertMany([{"Orderid":"1","fname":"Shubham","lname":"Banyal","email":"shubham.banyal@purplemail.ie","mobile":"0849937354",
                            "Address 1":"123 avenue eufbeu lane dbulin 23","Eircode":"D2345y65",
                            "Manufacturer 1":"Apple","Model 1":"Iphone 12","Price 1":"£1000",
                            "Manufacturer 2":"Samsung","Model 2":"Samsung Galaxy s20","Price 2":"£1100"},

                            {"Orderid":"2","fname":"Iarlaith","lname":"Kelly","email":"iarlaith.kelly@fuchsiamail.ie","mobile":"0843977120",
                            "Address 1":"456 street dublin 24","Eircode":"D24Y73D",
                            "Manufacturer":"Samsung","Model":"Samsung Galaxy s20","Price":"£1100"},

                            {"Orderid":"3","fname":"Iarlaith","lname":"Kelly","email":"iarlaith.kelly@fuchsiamail.ie","mobile":"0843977120",
                            "Address 1":"456 street dublin 24","Eircode":"D24Y73D",
                            "Manufacturer ":"Oneplus","Model":"OnePlus 9 Pro","Price":"£900"},

                            {"Orderid":"4","fname":"Brigid","lname":"Flynn","email":"brigid.flynn@silvermail.ie","mobile":"0844020733",
                            "Address 1":"789 Apartment dublin 1","Eircode":"D1EX6D",
                            "Manufacturer 1":"Oneplus","Model 1":"OnePlus 9 Pro","Price 1":"£900",
                            "Manufacturer 2":"Apple","Model 2":"Iphone 12","Price 2":"£1000"},],
      function(err,result){
      assert.equal(err, null);
      assert.equal(4, result.result.n);
      assert.equal(4, result.ops.length);
      // all good if we get to here
      console.log("Inserted 3 Phone_Details into the collection");
      callback(result);
    });
  }
//
// findDocuments() : find documents in the "documents"
//                   collection (assuming it exists)
//
const findDocuments = function(db, callback) {
    // Get the documents collection
    const collection = db.collection('documents');
    // Find some documents
    collection.find({}).toArray(function(err, docs) {
      // using the assert module for testing
      assert.equal(err, null);
      // all good if we get to here
      console.log("Found the following records");
      console.log(docs)
      callback(docs);
    });
}


//
// findDocumentsFiltered() : find documents in the "documents"
//                           collection (assuming it exists) using filter
//
const findDocumentsFiltered = function(db, callback) {
    // Get the documents collection
    const collection1 = db.collection('documents');
    const collection2 = db.collection('Phone_Details');
    const collection3 = db.collection('Order_details');
    // Find some documents - with a filter
    collection1.find({'lname': 'Banyal'}).toArray(function(err, docs) {
      // using the assert module for testing
      assert.equal(err, null);
      console.log("Found the following records");
      // all good if we get to here
      console.log(docs);
      // callback(docs);
        collection2.find({'Model': 'OnePlus 9 Pro'}).toArray(function(err, docs) {
          // using the assert module for testing
          assert.equal(err, null);
          console.log("Found the following records");
          // all good if we get to here
          console.log(docs);
          // callback(docs);
          collection3.find({'Orderid': '3'}).toArray(function(err, docs) {
            // using the assert module for testing
            assert.equal(err, null);
            console.log("Found the following records");
            // all good if we get to here
            console.log(docs);
            callback(docs);
        });
      });
    });
} 


//
// updateDocument() : update documents in the "documents"
//                    collection (assuming it exists)
//
const updateDocument = function(db, callback) {
    // Get the documents collection
    const collection1 = db.collection('documents');
    const collection2 = db.collection('Phone_Details');
    const collection3 = db.collection('Order_details');
    // Update document where email is "alondra.dunne@purplemail.ie", set to "alondra.dunne@redmail.ie"
    collection1.updateOne({email : "brigid.flynn@silvermail.ie" }
      , { $set: { email :  "alondra.dunne@redmail.ie" } }, function(err, result) {
      // using the assert module for testing
      // assert.equal(err, null);
      // assert.equal(1, result.result.n);
      // all good if we get to here
      console.log("Updated the document: reset email field set to alondra.dunne@redmail.ie");
      // callback(result);
        collection2.updateOne({Price: "£1000"}
        ,{$set:{Price : "£1200"}},function(err,result){
          // assert.equal(err, null);
          // assert.equal(1, result.result.n);
          // all good if we get to here
          console.log("Updated the Price of Iphone ");
          // callback(result);
          collection3.updateOne({Price1: "£1000"}
          ,{$set:{Price1: "£1200"}},function(err,result){
            // assert.equal(err, null);
            // assert.equal(1, result.result.n);
            // all good if we get to here
            console.log("Updated the Price of order of iphone: ");
            callback(result);
          });
        });
    });  
  }




//
// deleteDocument() : update documents in the "documents"
//                    collection (assuming it exists)
//
const removeDocument = function(db, callback) {
    // Get the documents collection
    const collection1 = db.collection('documents');
    const collection2 = db.collection('Phone_Details');
    const collection3 = db.collection('Order_details');
    // Delete document where email is "alondra.dunne@redmail.ie"
    collection1.deleteOne({ email : "iarlaith.kelly@fuchsiamail.ie" }, function(err, result) {
      // using the assert module for testing
      assert.equal(err, null);
      assert.equal(1, result.result.n);
      // all good if we get to here      
      console.log("Removed the document with email : 'iarlaith.kelly@fuchsiamail.ie'");
      // callback(result);
      collection2.deleteOne({Manufacturer: "Samsung"},function(err,result){
              // using the assert module for testing
        assert.equal(err, null);
        assert.equal(1, result.result.n);
        // all good if we get to here      
        console.log("Removed the document with manufacturere : 'Samsung");
      })
        collection3.deleteOne({Orderid: "2"},function(err,result){
            // using the assert module for testing
          assert.equal(err, null);
          assert.equal(1, result.result.n);
          // all good if we get to here      
          console.log("Removed the order from Order details : 'Order 2");
        })
    });    
}
