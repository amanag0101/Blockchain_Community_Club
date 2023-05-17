import Image from "next/image";
import styles from "./box.module.css";

interface Props {
    image: string,
    heading: string,
    description: string,
    height: number,
    width: number
}

export default function Box(props: Props) {
    return (
        <div className={styles.box}>
            <div className={styles.image}><Image src={props.image} alt={""} width={props.width} height={props.height}/></div>
            <div className={styles.heading}><p><b>{props.heading}</b></p></div>
            <div className={styles.description}><p>{props.description}</p></div>
        </div>
    );
}