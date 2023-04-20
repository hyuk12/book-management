import React from 'react';

const PromiseStudy = () => {

    const a = new Promise((resolve, reject) => {
        console.log('promise started');

        if ((1 === 1)) {
            resolve('promise resolved');

        } else {
            throw(new Error('promise rejected'));
        }

    });

    const b = (str) => {
        console.log(str);
    }

    const clickHandle = () => {
        a.then(() => {
            console.log('first then');
            return new Promise((resolve, reject) => {
                resolve('return');
            });
        })
            .catch((error) => {
                console.log(error);
            })
            .then(b);
    }

    return (
        <div>
            <button onClick={clickHandle}>버튼</button>
        </div>
    );
};

export default PromiseStudy;