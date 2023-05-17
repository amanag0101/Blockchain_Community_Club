"use client";
import Link from "next/link";
import styles from "./header.module.css";
import Image from "next/image";
import { useState } from "react";

export default function Header() {
  const [slideShowImage, setSlideShowImage] = useState<string>("/bg-1.jpg");

  return (
    <div className={styles.header}>
      <div className={styles.bg}>
        <Image src={slideShowImage} alt="Image" width={100} height={100} />
      </div>
      <div className={styles.navbar}>
        <div className={styles["col-1"]}>
          <div className={styles.branding}>
            <div className={styles.logo}><Image src="/layers.png" alt="logo" width={20} height={20}/></div>
            <div className={styles.title}><p>Stack</p></div>
          </div>
        </div>
        <div className={styles["col-2"]}>
          <Link href="#">Home</Link>
          <Link href="#">Pages</Link>
          <Link href="#">Contact</Link>
          <Link className={styles["login"]} href="#">
            LOG IN
          </Link>
          <Link className={styles["signup"]} href="#">
            SIGN UP
          </Link>
        </div>
      </div>

      <div className={styles.content}>
        <p className={styles.text}>We Create Interfaces</p>

        <div className={styles.dots}>
            <p className={styles.dot} onClick={() => setSlideShowImage("/bg-1.jpg")}></p> 
            <p className={styles.dot} onClick={() => setSlideShowImage("/bg-2.jpg")}></p> 
            <p className={styles.dot} onClick={() => setSlideShowImage("/bg-3.jpg")}></p> 
        </div>
      </div>
    </div>
  );
}
