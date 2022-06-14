var express = require('express')
const bodyParser = require('body-parser');
var cors = require('cors');
var app = express();
app.use(cors());
app.use(bodyParser.urlencoded({ extended: true }));

app.post('/login', function (req, res) {
    console.log(req.body)
    let email = req.body.email;
    let password = req.body.password;
    console.log(req.body.email)
    console.log(req.body.password)
    if (email.toLowerCase().trim() == 'joe' && password == 'blo') {
        res.status(200).send(true)
    } else {
        res.status(200).send(false)
    }
})
app.get('/login', function (req, res) {
    console.log('get pas supporté')
    res.status(500).send()
})

app.listen(3000, function() {
    console.log('It worked!')
})