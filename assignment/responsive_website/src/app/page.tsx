import Header from './components/Header';
import Services from './components/Services';
import styles from './page.module.css';

export default function Home() {
  return (
    <main className={styles.home}>
      <div className={styles.header}>
        <Header />
      </div>

      <div className={styles.services}>
        <Services />
      </div>
    </main>
  )
}
