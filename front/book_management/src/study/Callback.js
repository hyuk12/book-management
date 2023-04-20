import React, {useState} from 'react';



const Callback = () => {
    const [num, setNum] = useState(0);
    let num1 = 0;
    const a = (fx, fx2) => {
        console.log('A함수 실행');
        setNum(() => fx(fx2));
    }

    const b = (fx2) => {
        console.log('B함수 실행');
        num1 = num + 100;
        fx2();
        return num1;
    }

    const c = () => {
        console.log("C함수 실행");
        console.log('num: ', num1);
    }

    const clickHandle = () => {
        a(b,c);
    }

    return (
        <div>
            <button onClick={clickHandle}>버튼</button>
        </div>
    );
};

export default Callback;