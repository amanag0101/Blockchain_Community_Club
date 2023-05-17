import Box from "./Box";
import styles from "./services.module.css";

export default function Services() {
  return (
    <div className={styles.services}>
      <p className={styles.title}><b>Our Services</b></p>

      <div className={styles.content}>
        <div className={styles.box}>
          <Box
            image={"laptop-mobile.svg"}
            heading={"Responsive Design"}
            description={
              "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. "
            }
            height={50}
            width={50}
          />
        </div>

        <div className={styles.box}>
          <Box
            image={"chart-histogram.svg"}
            heading={"Business Solutions"}
            description={
              "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. "
            }
            height={50}
            width={50}
          />
        </div>

        <div className={styles.box}>
          <Box
            image={"drafting-compass.svg"}
            heading={"Brand Identity"}
            description={
              "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. "
            }
            height={50}
            width={50}
          />
        </div>
      </div>
    </div>
  );
}
