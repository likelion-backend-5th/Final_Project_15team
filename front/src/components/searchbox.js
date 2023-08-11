import * as React from "react";
import Box from "@mui/material/Box";
import TextField from "@mui/material/TextField";

export default function Searchbox() {
  return (
    <Box
      sx={{
        width: "40rem",
        maxWidth: "100%",
        background: "white",
        borderRadius: "1rem",
        margin: "auto",
        marginBottom: "1rem",
        border: "none",
      }}>
      <TextField fullWidth label="#검색" id="searchbox" />
    </Box>
  );
}
